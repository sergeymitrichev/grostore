package ru.ftob.grostore.service.xlsto;

import com.poiji.annotation.ExcelCell;

public class XlsProductSnapshotRow {

    @ExcelCell(0)
    private String first;

    @ExcelCell(1)
    private String second;

    @ExcelCell(2)
    private String third;

    @ExcelCell(3)
    private String fourth;

    @ExcelCell(4)
    private String fifth;

    public XlsProductSnapshotRow() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third;
    }

    public String getFourth() {
        return fourth;
    }

    public void setFourth(String fourth) {
        this.fourth = fourth;
    }

    public String getFifth() {
        return fifth;
    }

    public void setFifth(String fifth) {
        this.fifth = fifth;
    }

    @Override
    public String toString() {
        return "XlsProductSnapshotRow{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                ", third='" + third + '\'' +
                ", fourth='" + fourth + '\'' +
                ", fifth='" + fifth + '\'' +
                '}';
    }
}
