package slepenkov.gleb.shopandproducts.search;

import slepenkov.gleb.shopandproducts.exceptions.ProductLimitReachedException;
import slepenkov.gleb.shopandproducts.exceptions.ProductNotFoundException;
import slepenkov.gleb.shopandproducts.exceptions.ProductWithKeywordNotFound;
import slepenkov.gleb.shopandproducts.products.Product;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchIndex<PT extends Product> implements Index<PT> {
    private final Map<String, Set<PT>> index = new HashMap<>();
    private final Set<PT> listOfProducts = new HashSet<>();
    private final int limit;

    public SearchIndex(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchIndex<?> that = (SearchIndex<?>) o;
        return limit == that.limit && Objects.equals(index, that.index) && Objects.equals(listOfProducts, that.listOfProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, listOfProducts, limit);
    }

    public String printList() {
        StringBuilder result = new StringBuilder();
        result.append("Список товаров: [\n");
        for (var elem : listOfProducts) {
            result.append(elem + "\n");
        }
        result.append("]");
        return result.toString();
    }

    public String printIndex() {
        StringBuilder result = new StringBuilder();
        result.append("Поисковый индекс: {\n");
        for (var key : index.keySet()) {
            result.append("\t" + key + ":{\n");
            for (var elem : index.get(key)) {
                result.append("\t\t" + elem.getName() + ",\n");
            }
            result.append("\t},\n");
        }
        result.append("}");
        return result.toString();
    }

    public Stream<PT> getCompetitors(PT competitor) {
        return Stream.of(competitor)
                .flatMap(elem -> Arrays.asList(elem.getName().toLowerCase().split(" ")).stream())
                .flatMap(token -> index.get(token).stream())
                .collect(Collectors.toMap(Function.identity(), count -> 1, (oldValue, newValue) -> oldValue + 1))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry<PT, Integer>::getValue).reversed())
                .map(Map.Entry::getKey)
                .filter(elem -> !elem.equals(competitor));
    }

    public void add(PT product) {
        if (listOfProducts.size() < limit) {
            listOfProducts.add(product);
            String[] tokens = product.getName().split(" ");
            for (var token : tokens) {
                String lcToken = token.toLowerCase();
                Set<PT> productsWithToken = index.getOrDefault(lcToken, new HashSet<>());
                productsWithToken.add(product);
                index.put(lcToken, productsWithToken);
            }
        } else {
            throw new ProductLimitReachedException();
        }
    }

    public int size() {
        return listOfProducts.size();
    }

    public <RT extends Product> List<RT> filterBy(Predicate<PT> condition) {
        List<RT> result = new ArrayList<>();
        for (var elem : listOfProducts) {
            if (condition.test(elem)) {
                result.add((RT) elem);
            }
        }
        return result;
    }

    public List<PT> find(String key) {
        List<PT> buffer = new ArrayList<>();
        buffer.addAll(index.get(key));
        return buffer;
    }

    public List<PT> find(Predicate<String> condition) {
        List<PT> result = new ArrayList<>();
        Set<PT> buffer = new HashSet<>();
        for (var key : index.keySet()) {
            if (condition.test(key)) {
                buffer.addAll(index.get(key));
            }
        }
        result.addAll(buffer);
        return result;
    }

    public List<PT> findByPattern(String pattern) {
        String lcPattern = pattern;
        if (pattern.equals("*")) {
            return find(elem -> !elem.isEmpty());
        } else if (!pattern.contains("*")) {
            return find(elem -> elem.equals(lcPattern));
        } else if (pattern.startsWith("*")) {
            return find(elem -> elem.endsWith(lcPattern.substring(1)));
        } else if (pattern.endsWith("*")) {
            return find(elem -> elem.startsWith(lcPattern.substring(0, lcPattern.length() - 1)));
        } else {
            String[] splittedPattern = lcPattern.split("\\*");
            return find(elem -> elem.startsWith(splittedPattern[0])
                    && elem.endsWith(splittedPattern[1]));
        }
    }

    private void clear(Collection<PT> list) {
        for (var product : list) {
            String[] tokens = product.getName().split(" ");
            for (var token : tokens) {
                String lcToken = token.toLowerCase();
                index.computeIfPresent(lcToken, (k, v) -> {
                    v.remove(product);
                    return v;
                });
                if (index.get(lcToken).isEmpty()) {
                    index.remove(lcToken);
                }
            }
            listOfProducts.remove(product);
        }
    }

    public void delete(String key) {
        Set<PT> products = index.get(key);
        if (products == null) {
            throw new ProductWithKeywordNotFound(key);
        }
        clear(products);
    }

    public void delete(Predicate predicate) {
        List<PT> products = filterBy(predicate);
        if (products == null) {
            throw new ProductNotFoundException();
        }
        clear(products);
    }

    public String toString() {
        return printList() + "\n" + printIndex();
    }
}
