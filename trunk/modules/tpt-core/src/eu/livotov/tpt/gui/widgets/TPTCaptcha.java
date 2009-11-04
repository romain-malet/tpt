/*******************************************************************************
 * Copyright 2009, Dmitri Livotov
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 ******************************************************************************/

package eu.livotov.tpt.gui.widgets;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Embedded;
import eu.livotov.tpt.TPTApplication;
import eu.livotov.tpt.data.util.CaptchaImageGenerator;
import eu.livotov.tpt.data.util.RandomPasswordGenerator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * This component represents a captcha image that can be displayed for some form validations. You
 * can provide your own text for captcha image or let the component to automatically generat it to
 * you.
 */
public class TPTCaptcha extends Embedded implements StreamResource.StreamSource
{
    /**
     * Image provider for captcha
     */
    private CaptchaImageProvider imageProvider = new CaptchaImageGenerator ();

    /**
     * Current captcha code
     */
    private String captchaCode = "";

    /**
     * Creates a captcha component with random 5-letter code generated
     */
    public TPTCaptcha ()
    {
        super ();
        generateCaptchaCode ( 5 );
    }

    /**
     * Creates a captcha component with the specified text to be used as captcha code
     *
     * @param code captcha code
     */
    public TPTCaptcha ( String code )
    {
        this ();
        setCaptchaCode ( code );
    }

    /**
     * Sets the new captcha code. Image will be regenerated automatically.
     *
     * @param code new captcha code
     */
    public void setCaptchaCode ( String code )
    {
        captchaCode = code;
        setSource ( new StreamResource ( this, UUID.randomUUID ().toString () + ".jpg",
                TPTApplication.getCurrentApplication () ) );
    }

    /**
     * Provides the current captcha code that is displayed in the component
     *
     * @return current captcha code
     */
    public String getCaptchaCode ()
    {
        return captchaCode;
    }

    /**
     * Verifies the given code agains current captcha code.
     *
     * @param sample sample text to compare with the current captcha component
     * @return <code>true</code> if provided code matches the captcha (uses case-insensitive
     *         comparison)
     */
    public boolean verifyCaptchaCode ( final String sample )
    {
        return sample != null && sample.equalsIgnoreCase ( getCaptchaCode () );
    }

    /**
     * Generates and sets a random captcha code with the specified characters length.
     *
     * @param charactersCount number of characters in the new code
     * @return generated code. Note, that this method will also set the newly generated code to a
     *         component.
     */
    public String generateCaptchaCode ( int charactersCount )
    {
        setCaptchaCode ( RandomPasswordGenerator.generate ( charactersCount ) );
        return getCaptchaCode ();
    }

    /**
     * Sets the new image provider, that is responsible for creating captcha images. TPTCaptcha
     * compnent has its own default image generator, but you may specify your own implementation if
     * you wish.
     *
     * @param provider new image provider to use. Captcha code will be regenerated using this new
     *                 image provider immideately.
     */
    public void setCaptchaImageProvider ( CaptchaImageProvider provider )
    {
        imageProvider = provider;
        setCaptchaCode ( getCaptchaCode () );
    }

    public InputStream getStream ()
    {
        BufferedImage image = imageProvider.getCaptchaImage ( "" + captchaCode );
        ByteArrayOutputStream bos = new ByteArrayOutputStream ();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder ( bos );

        try
        {
            encoder.encode ( image );
        }
        catch ( IOException e )
        {
            throw new RuntimeException ( "Captcha generation error: " + e.getMessage (), e );
        }
        return new ByteArrayInputStream ( bos.toByteArray () );
    }

    /**
     * API for connecting custom image generators. Use setImageProvider method of the TPTCaptcha
     * component to set the new image provider if you want the captcha images to be generated
     * differently.
     */
    public interface CaptchaImageProvider
    {
        /**
         * Should provide a BufferedImage that represens the capctha code.
         *
         * @param text text to encode in the image
         * @return encoded captcha image to be displayed
         */
        BufferedImage getCaptchaImage ( String text );
    }
}
