package ru.ftob.grostore.rest.service;

import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.rest.webmodel.GuiPriceList;

public interface PriceListService {

    GuiPriceList init(MultipartFile file);

    GuiPriceList save(GuiPriceList priceList);

    GuiPriceList get(int id);

    void delete(int id);

    GuiPriceList upload(GuiPriceList priceList);

}
