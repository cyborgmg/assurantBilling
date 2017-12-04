
package br.com.delphos.billing.braspag.conciliador.cliente;

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
 *         &lt;element name="CorrelatedId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ErrorReportCollection" type="{https://reconciliation.braspag.com.br}ArrayOfErrorReport" minOccurs="0"/>
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
    "correlatedId",
    "success",
    "errorReportCollection"
})
@XmlSeeAlso({
    GetExportedFileResponse2 .class,
    DownloadTransactionFileResponse.class,
    GetExportedFileV2Response2 .class,
    StoreTransactionFileResponse.class
})
public abstract class AbstractResponse {

    @XmlElement(name = "CorrelatedId", required = true)
    protected String correlatedId;
    @XmlElement(name = "Success")
    protected boolean success;
    @XmlElement(name = "ErrorReportCollection")
    protected ArrayOfErrorReport errorReportCollection;

    /**
     * Gets the value of the correlatedId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrelatedId() {
        return correlatedId;
    }

    /**
     * Sets the value of the correlatedId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrelatedId(String value) {
        this.correlatedId = value;
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
     * Gets the value of the errorReportCollection property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfErrorReport }
     *     
     */
    public ArrayOfErrorReport getErrorReportCollection() {
        return errorReportCollection;
    }

    /**
     * Sets the value of the errorReportCollection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfErrorReport }
     *     
     */
    public void setErrorReportCollection(ArrayOfErrorReport value) {
        this.errorReportCollection = value;
    }

}
