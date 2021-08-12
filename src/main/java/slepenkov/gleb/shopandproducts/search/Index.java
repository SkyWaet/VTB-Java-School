package slepenkov.gleb.shopandproducts.search;

import slepenkov.gleb.shopandproducts.products.Product;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface Index<PT extends Product> extends Storage<PT> {
    String printIndex();

    List<PT> find(Predicate<String> condition);

    <RT extends Product> List<RT> filterBy(Predicate<PT> condition);

    Stream<PT> getCompetitors(PT competitor);

    List<PT> findByPattern(String pattern);
}
