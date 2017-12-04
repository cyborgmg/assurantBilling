
package br.com.delphos.billing.braspag.conciliador;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventCategory.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EventCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Undefined"/>
 *     &lt;enumeration value="Adjust"/>
 *     &lt;enumeration value="ServiceFee"/>
 *     &lt;enumeration value="Capture"/>
 *     &lt;enumeration value="Payment"/>
 *     &lt;enumeration value="BatchPayment"/>
 *     &lt;enumeration value="Acceleration"/>
 *     &lt;enumeration value="Unscheduling"/>
 *     &lt;enumeration value="Reversal"/>
 *     &lt;enumeration value="Chargeback"/>
 *     &lt;enumeration value="Anticipation"/>
 *     &lt;enumeration value="BatchAnticipation"/>
 *     &lt;enumeration value="BatchAdjustment"/>
 *     &lt;enumeration value="Rescheduling"/>
 *     &lt;enumeration value="AnticipationOperationCost"/>
 *     &lt;enumeration value="RetainedValue"/>
 *     &lt;enumeration value="RetainedValuePayment"/>
 *     &lt;enumeration value="RetainedValueDebit"/>
 *     &lt;enumeration value="AnticipatedAdjusts"/>
 *     &lt;enumeration value="InstallmentsRounding"/>
 *     &lt;enumeration value="AnticipatedReversal"/>
 *     &lt;enumeration value="UndefinedAdjust"/>
 *     &lt;enumeration value="AccumulatedDebit"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EventCategory")
@XmlEnum
public enum EventCategory {

    @XmlEnumValue("Undefined")
    UNDEFINED("Undefined"),
    @XmlEnumValue("Adjust")
    ADJUST("Adjust"),
    @XmlEnumValue("ServiceFee")
    SERVICE_FEE("ServiceFee"),
    @XmlEnumValue("Capture")
    CAPTURE("Capture"),
    @XmlEnumValue("Payment")
    PAYMENT("Payment"),
    @XmlEnumValue("BatchPayment")
    BATCH_PAYMENT("BatchPayment"),
    @XmlEnumValue("Acceleration")
    ACCELERATION("Acceleration"),
    @XmlEnumValue("Unscheduling")
    UNSCHEDULING("Unscheduling"),
    @XmlEnumValue("Reversal")
    REVERSAL("Reversal"),
    @XmlEnumValue("Chargeback")
    CHARGEBACK("Chargeback"),
    @XmlEnumValue("Anticipation")
    ANTICIPATION("Anticipation"),
    @XmlEnumValue("BatchAnticipation")
    BATCH_ANTICIPATION("BatchAnticipation"),
    @XmlEnumValue("BatchAdjustment")
    BATCH_ADJUSTMENT("BatchAdjustment"),
    @XmlEnumValue("Rescheduling")
    RESCHEDULING("Rescheduling"),
    @XmlEnumValue("AnticipationOperationCost")
    ANTICIPATION_OPERATION_COST("AnticipationOperationCost"),
    @XmlEnumValue("RetainedValue")
    RETAINED_VALUE("RetainedValue"),
    @XmlEnumValue("RetainedValuePayment")
    RETAINED_VALUE_PAYMENT("RetainedValuePayment"),
    @XmlEnumValue("RetainedValueDebit")
    RETAINED_VALUE_DEBIT("RetainedValueDebit"),
    @XmlEnumValue("AnticipatedAdjusts")
    ANTICIPATED_ADJUSTS("AnticipatedAdjusts"),
    @XmlEnumValue("InstallmentsRounding")
    INSTALLMENTS_ROUNDING("InstallmentsRounding"),
    @XmlEnumValue("AnticipatedReversal")
    ANTICIPATED_REVERSAL("AnticipatedReversal"),
    @XmlEnumValue("UndefinedAdjust")
    UNDEFINED_ADJUST("UndefinedAdjust"),
    @XmlEnumValue("AccumulatedDebit")
    ACCUMULATED_DEBIT("AccumulatedDebit");
    private final String value;

    EventCategory(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventCategory fromValue(String v) {
        for (EventCategory c: EventCategory.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
