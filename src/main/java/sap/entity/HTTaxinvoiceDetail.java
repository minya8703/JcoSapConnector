package sap.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HTTaxinvoiceDetail {
    private String serialNum;
    private String purchaseDT;
    private String itemName;
    private String spec;
    private String qty;
    private String unitCost;
    private String supplyCost;
    private String tax;
    private String remark;
}
