
package br.com.delphos.billing.braspag.conciliador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProcessingType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProcessingType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Daily"/>
 *     &lt;enumeration value="Reprocessed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProcessingType")
@XmlEnum
public enum ProcessingType {

    @XmlEnumValue("Daily")
    DAILY("Daily"),
    @XmlEnumValue("Reprocessed")
    REPROCESSED("Reprocessed");
    private final String value;

    ProcessingType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProcessingType fromValue(String v) {
        for (ProcessingType c: ProcessingType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
