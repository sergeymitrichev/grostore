package ru.ftob.grostore.rest.service;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.ftob.grostore.rest.webmodel.GuiPriceList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PriceListServiceImpl implements PriceListService {
    @Override
    public GuiPriceList init(MultipartFile file) {
        GuiPriceList guiPriceList = new GuiPriceList();
        guiPriceList.setFileName(file.getOriginalFilename());
        try {
            HSSFWorkbook  workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while(rowIterator.hasNext()) {
                //TODO if ROW is empty
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> guiPriceListRow = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    //TODO if CELL is empty
                    Cell cell = cellIterator.next();
                    int cellType = cell.getCellType();
                    switch (cellType) {
                        case Cell.CELL_TYPE_STRING:
                            guiPriceListRow.add(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            guiPriceListRow.add(String.valueOf(cell.getNumericCellValue()));
                            break;

                        case Cell.CELL_TYPE_FORMULA:
                            guiPriceListRow.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        default:
                            guiPriceListRow.add("");
                            break;
                    }
                }
                guiPriceList.setTableRow(guiPriceListRow);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return guiPriceList;
    }

    @Override
    public GuiPriceList save(GuiPriceList priceList) {
        return null;
    }

    @Override
    public GuiPriceList get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public GuiPriceList upload(GuiPriceList priceList) {
        return null;
    }
}
