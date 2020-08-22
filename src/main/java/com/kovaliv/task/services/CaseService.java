package com.kovaliv.task.services;

import com.kovaliv.task.entities.Case;
import com.kovaliv.task.entities.Orderline;
import com.kovaliv.task.entities.Product;
import com.kovaliv.task.repos.CaseRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CaseService {
    private final CaseRepo caseRepo;

    public Case getCase(Orderline orderline) {
        Product product = orderline.getProduct();
        Double volume = countVolume(product) * orderline.getNumberOfProducts();
        List<Case> cases = caseRepo.findAll().stream()
                .filter(c -> countVolume(c) > volume)
                .filter(c -> canContain(c, product))
                .filter(c -> canContain(c, orderline))
                .sorted(Comparator.comparing(this::countVolume))
                .collect(Collectors.toList()
                );
        return cases.get(0);
    }

    private Double countVolume(Product product) {
        return product.getSizeX() * product.getSizeY() * product.getSizeZ();
    }

    private Double countVolume(Case mycase) {
        return mycase.getSizeX() * mycase.getSizeY() * mycase.getSizeZ();
    }

    private Boolean canContain(Case mycase, Product product) {
        List<Double> params1 = Arrays.asList(mycase.getSizeX(), mycase.getSizeY(), mycase.getSizeZ()).stream().sorted().collect(Collectors.toList());
        List<Double> params2 = Arrays.asList(product.getSizeX(), product.getSizeY(), product.getSizeZ()).stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < params1.size(); i++) {
            if (params1.get(i) < params2.get(i)) {
                return false;
            }
        }
        return true;
    }

    private Boolean canContain(Case mycase, Orderline orderline) {
        Product product = orderline.getProduct();
        Double x = product.getSizeX(), y = product.getSizeY(), z = product.getSizeZ();

        Product productEnd = new Product((Long) 1L, x, y, z, new ArrayList<Orderline>());
        for (int i = 0; i < orderline.getNumberOfProducts(); i++) {
            if (!canContain(mycase, new Product((Long) 1L, (Double) (productEnd.getSizeX() + x), productEnd.getSizeY(), productEnd.getSizeZ(), new ArrayList<Orderline>())) &&
                    !canContain(mycase, new Product((Long) 1L, productEnd.getSizeX(), (Double) (productEnd.getSizeY() + y), productEnd.getSizeZ(), new ArrayList<Orderline>())) &&
                    !canContain(mycase, new Product((Long) 1L, productEnd.getSizeX(), productEnd.getSizeY(), (Double) (productEnd.getSizeZ() + z), new ArrayList<Orderline>()))
            ) return false;
            if (canContain(mycase, new Product((Long) 1L, (Double) (productEnd.getSizeX() + x), productEnd.getSizeY(), productEnd.getSizeZ(), new ArrayList<Orderline>()))) {
                productEnd = new Product((Long) 1L, (Double) (productEnd.getSizeX() + x), productEnd.getSizeY(), productEnd.getSizeZ(), new ArrayList<Orderline>());
                continue;
            }
            if (canContain(mycase, new Product((Long) 1L, productEnd.getSizeX(), (Double) (productEnd.getSizeY() + y), productEnd.getSizeZ(), new ArrayList<Orderline>()))) {
                productEnd = new Product((Long) 1L, productEnd.getSizeX(), (Double) (productEnd.getSizeY() + y), productEnd.getSizeZ(), new ArrayList<Orderline>());
                continue;
            }
            if (canContain(mycase, new Product((Long) 1L, productEnd.getSizeX(), productEnd.getSizeY(), (Double) (productEnd.getSizeZ() + z), new ArrayList<Orderline>()))) {
                productEnd = new Product((Long) 1L, productEnd.getSizeX(), productEnd.getSizeY(), (Double) (productEnd.getSizeZ() + z), new ArrayList<Orderline>());
                continue;
            }
        }
        return true;
    }
}
