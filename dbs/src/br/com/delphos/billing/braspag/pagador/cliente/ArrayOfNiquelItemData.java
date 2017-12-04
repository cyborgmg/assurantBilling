
package br.com.delphos.billing.braspag.pagador.cliente;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfNiquelItemData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfNiquelItemData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NiquelItemData" type="{https://www.pagador.com.br/webservice/pagador}NiquelItemData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfNiquelItemData", propOrder = {
    "niquelItemData"
})
public class ArrayOfNiquelItemData {

    @XmlElement(name = "NiquelItemData", nillable = true)
    protected List<NiquelItemData> niquelItemData;

    /**
     * Gets the value of the niquelItemData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the niquelItemData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNiquelItemData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NiquelItemData }
     * 
     * 
     */
    public List<NiquelItemData> getNiquelItemData() {
        if (niquelItemData == null) {
            niquelItemData = new ArrayList<NiquelItemData>();
        }
        return this.niquelItemData;
    }

}
