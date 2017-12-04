
package br.com.delphos.billing.braspag.conciliador.cliente;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AbstractRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestId" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="RequestingUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractRequest", propOrder = {
    "requestId",
    "requestingUserName"
})
@XmlSeeAlso({
    GetExportedFileRequest.class,
    GetExportedFileV2Request.class,
    DownloadTransactionFileRequest.class,
    StoreTransactionFileRequest.class
})
public abstract class AbstractRequest {

    @XmlElement(name = "RequestId", required = true)
    protected String requestId;
    @XmlElement(name = "RequestingUserName")
    protected String requestingUserName;

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the requestingUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestingUserName() {
        return requestingUserName;
    }

    /**
     * Sets the value of the requestingUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestingUserName(String value) {
        this.requestingUserName = value;
    }

}
