
package br.com.delphos.billing.braspag.pagador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AbstractResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CorrelationId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ErrorReportDataCollection" type="{https://www.pagador.com.br/webservice/pagador}ArrayOfErrorReportDataResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractResponse", propOrder = {
    "correlationId",
    "success",
    "errorReportDataCollection"
})
@XmlSeeAlso({
    AuthorizeTransactionResponse2 .class,
    RefundCreditCardTransactionResponse2 .class,
    VoidCreditCardTransactionResponse2 .class,
    CaptureCreditCardTransactionResponse2 .class
})
public abstract class AbstractResponse {

    @XmlElement(name = "CorrelationId", required = true)
    protected String correlationId;
    @XmlElement(name = "Success")
    protected boolean success;
    @XmlElement(name = "ErrorReportDataCollection")
    protected ArrayOfErrorReportDataResponse errorReportDataCollection;

    /**
     * Gets the value of the correlationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelationId() {
        return correlationId;
    }

    /**
     * Sets the value of the correlationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
    }

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the errorReportDataCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfErrorReportDataResponse }
     *     
     */
    public ArrayOfErrorReportDataResponse getErrorReportDataCollection() {
        return errorReportDataCollection;
    }

    /**
     * Sets the value of the errorReportDataCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfErrorReportDataResponse }
     *     
     */
    public void setErrorReportDataCollection(ArrayOfErrorReportDataResponse value) {
        this.errorReportDataCollection = value;
    }

}
