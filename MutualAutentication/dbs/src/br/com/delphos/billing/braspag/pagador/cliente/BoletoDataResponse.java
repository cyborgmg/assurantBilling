
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BoletoDataResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BoletoDataResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://www.pagador.com.br/webservice/pagador}PaymentDataResponse">
 *       &lt;sequence>
 *         &lt;element name="BoletoNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BoletoExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BoletoUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BarCodeNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Assignor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoletoDataResponse", propOrder = {
    "boletoNumber",
    "boletoExpirationDate",
    "boletoUrl",
    "barCodeNumber",
    "assignor",
    "message"
})
public class BoletoDataResponse
    extends PaymentDataResponse
{

    @XmlElement(name = "BoletoNumber")
    protected String boletoNumber;
    @XmlElement(name = "BoletoExpirationDate")
    protected String boletoExpirationDate;
    @XmlElement(name = "BoletoUrl")
    protected String boletoUrl;
    @XmlElement(name = "BarCodeNumber")
    protected String barCodeNumber;
    @XmlElement(name = "Assignor")
    protected String assignor;
    @XmlElement(name = "Message")
    protected String message;

    /**
     * Gets the value of the boletoNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoletoNumber() {
        return boletoNumber;
    }

    /**
     * Sets the value of the boletoNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoletoNumber(String value) {
        this.boletoNumber = value;
    }

    /**
     * Gets the value of the boletoExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoletoExpirationDate() {
        return boletoExpirationDate;
    }

    /**
     * Sets the value of the boletoExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoletoExpirationDate(String value) {
        this.boletoExpirationDate = value;
    }

    /**
     * Gets the value of the boletoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoletoUrl() {
        return boletoUrl;
    }

    /**
     * Sets the value of the boletoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoletoUrl(String value) {
        this.boletoUrl = value;
    }

    /**
     * Gets the value of the barCodeNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarCodeNumber() {
        return barCodeNumber;
    }

    /**
     * Sets the value of the barCodeNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarCodeNumber(String value) {
        this.barCodeNumber = value;
    }

    /**
     * Gets the value of the assignor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignor() {
        return assignor;
    }

    /**
     * Sets the value of the assignor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignor(String value) {
        this.assignor = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
