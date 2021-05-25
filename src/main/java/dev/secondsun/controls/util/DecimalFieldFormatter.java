package dev.secondsun.controls.util;

import javafx.scene.control.TextFormatter;

import java.text.DecimalFormat;
import java.text.ParsePosition;

public class DecimalFieldFormatter extends TextFormatter<String> {

    public DecimalFieldFormatter() {
        super(c ->
        {
            var format = new DecimalFormat( "#.0" );
            if ( c.getControlNewText().isEmpty() )
            {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = format.parse( c.getControlNewText(), parsePosition );

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        });
    }
}
