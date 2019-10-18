package com.ericsson.mts.nas.informationelement.field.translator;

import com.ericsson.mts.nas.BitInputStream;
import com.ericsson.mts.nas.exceptions.DecodingException;
import com.ericsson.mts.nas.informationelement.field.AbstractTranslatorField;
import com.ericsson.mts.nas.reader.XMLFormatReader;
import com.ericsson.mts.nas.registry.Registry;
import com.ericsson.mts.nas.writer.FormatWriter;
import com.ericsson.mts.nas.writer.XMLFormatWriter;

import javax.xml.bind.DatatypeConverter;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.math.BigInteger;

public class DigitsField extends AbstractTranslatorField {

    @Override
    public int decode(Registry mainRegistry, BitInputStream bitInputStream, FormatWriter formatWriter) throws IOException, DecodingException {
        logger.trace("Enter field {} with length {}", name, length);
        int val = bitInputStream.bigReadBits(length).intValueExact();
        formatWriter.intValue(name, BigInteger.valueOf(val));
        logger.trace("Result : " + val);
        return 0;
    }

    @Override
    public String encode(Registry mainRegistry, XMLFormatReader r, StringBuilder binaryString) {
        String value = r.stringValue(name);
        return String.format("%"+length+"s", Integer.toBinaryString(Integer.valueOf(value).byteValue() & 0xFF)).replace(' ', '0');
    }
}
