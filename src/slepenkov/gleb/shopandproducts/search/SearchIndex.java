package slepenkov.gleb.shopandproducts.search;

import slepenkov.gleb.shopandproducts.exceptions.ProductLimitReachedException;
import slepenkov.gleb.shopandproducts.exceptions.ProductNotFoundException;
import slepenkov.gleb.shopandproducts.exceptions.ProductWithKeywordNotFound;
import slepenkov.gleb.shopandproducts.products.Product;

import java.util.*;
import java.util.function.Predicate;

public class SearchIndex<PT extends Product> {
    private final Map<String, Set<PT>> index = new HashMap<>();
    protected final Set<PT> list = new HashSet<>();
    private final int limit;

    public SearchIndex(int limit) {
        this.limit = limit;
    }

    public String printList() {
        StringBuilder result = new StringBuilder();
        result.append("Список товаров: [\n");
        for (var elem : list) {
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

    public void add(PT product) {
        if (list.size() < limit) {
            list.add(product);
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
        return list.size();
    }

    public <RT extends Product> List<RT> filterBy(Predicate<PT> condition) {
        List<RT> result = new ArrayList<>();
        for (var elem : list) {
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
        }
        else if (pattern.startsWith("*")) {
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
            list.remove(product);
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
