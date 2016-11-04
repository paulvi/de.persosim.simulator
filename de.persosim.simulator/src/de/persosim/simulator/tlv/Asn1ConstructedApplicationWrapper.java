package de.persosim.simulator.tlv;

/**
 * This abstract class provides support for encoding constructed ASN.1 data structures used to wrap a single primitive ASN.1 data structure, i.e. simply nested ASN.1 data structures.
 */
public abstract class Asn1ConstructedApplicationWrapper {
	
	protected Asn1Primitive asn1Primitive;
	
	/**
	 * Constructor for a primitive character string type object
	 * @param tlvTagInner the tag to use (must be primitive)
	 * @param pattern the regex pattern to match for (optional)
	 * @param charset the character set to use for byte[] encoding
	 */
	public Asn1ConstructedApplicationWrapper(Asn1Primitive asn1Primitive) {
		this.asn1Primitive = asn1Primitive;
	}
	
	public Asn1Primitive getAsn1Primitive() {
		return asn1Primitive;
	}
	
	/**
	 * This method returns an ASN.1 byte[] encoding of the object 
	 * @param input the value to be encoded
	 * @return the encoded byte[] representation
	 */
	public ConstructedTlvDataObject encode(TlvTag tlvTagOuter, String input) {
		if(!tlvTagOuter.indicatesEncodingConstructed()) {
			throw new IllegalArgumentException("provided outer TLV tag must be constructed");
		}
		
		ConstructedTlvDataObject ctlv = new ConstructedTlvDataObject(tlvTagOuter);
		PrimitiveTlvDataObject ptlv = asn1Primitive.encode(input);
		ctlv.addTlvDataObject(ptlv);
		
		return ctlv;
	}
	
}
