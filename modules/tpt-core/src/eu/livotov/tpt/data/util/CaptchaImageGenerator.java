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

package eu.livotov.tpt.data.util;

import eu.livotov.tpt.gui.widgets.TPTCaptcha;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


/**
 * Default image generator for TPTCaptchaComponent.
 */
public class CaptchaImageGenerator implements TPTCaptcha.CaptchaImageProvider
{
    private static final int LETTER_WIDTH = 50;
    private static final int IMAGE_HEIGHT = 60;
    private static final double SKEW = 2.5;
    private static final int DRAW_LINES = 4;
    private static final int DRAW_BOXES = 1;

    private static final Color[] RANDOM_BG_COLORS =
            { Color.RED, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK,
                    Color.YELLOW };

    private static final Color[] RANDOM_FG_COLORS = { Color.BLACK, Color.BLUE, Color.DARK_GRAY };

    public BufferedImage getCaptchaImage ( String code )
    {
        int MAX_LETTER_COUNT = code.length ();
        int MAX_X = LETTER_WIDTH * MAX_LETTER_COUNT;
        int MAX_Y = IMAGE_HEIGHT;

        BufferedImage outImage = new BufferedImage ( MAX_X, MAX_Y, BufferedImage.TYPE_INT_RGB );
        Graphics2D g2d = outImage.createGraphics ();
        g2d.setColor ( java.awt.Color.WHITE );
        g2d.fillRect ( 0, 0, MAX_X, MAX_Y );
        for ( int i = 0; i < DRAW_BOXES; i++ )
        {
            paindBoxes ( g2d, MAX_X, MAX_Y );
        }

        Font font = new Font ( "dialog", 1, 33 );
        g2d.setFont ( font );

        g2d.setColor ( Color.BLACK );
        g2d.drawRect ( 0, 0, ( MAX_X ) - 1, MAX_Y - 1 );

        AffineTransform affineTransform = new AffineTransform ();

        for ( int i = 0; i < MAX_LETTER_COUNT; i++ )
        {
            double angle = 0;
            if ( Math.random () * 2 > 1 )
            {
                angle = Math.random () * SKEW;
            }
            else
            {
                angle = Math.random () * -SKEW;
            }
            affineTransform
                    .rotate ( angle, ( LETTER_WIDTH * i ) + ( LETTER_WIDTH / 2 ), MAX_Y / 2 );
            g2d.setTransform ( affineTransform );
            setRandomFont ( g2d );
            setRandomFGColor ( g2d );
            g2d.drawString ( code.substring ( i, i + 1 ), ( i * LETTER_WIDTH ) + 3,
                    28 + ( int ) ( Math.random () * 6 ) );

            affineTransform
                    .rotate ( -angle, ( LETTER_WIDTH * i ) + ( LETTER_WIDTH / 2 ), MAX_Y / 2 );
        }

        g2d.setXORMode ( Color.RED );
        g2d.setStroke ( new BasicStroke ( 1 ) );
        g2d.drawLine ( 0, 0, MAX_X, MAX_Y );
        g2d.setXORMode ( Color.YELLOW );
        g2d.drawLine ( 0, MAX_Y, MAX_X, 0 );

        for ( int i = 0; i < DRAW_LINES; i++ )
        {
            g2d.setXORMode ( Color.RED );
            g2d.setStroke ( new BasicStroke ( 2 ) );
            int y1 = ( int ) ( Math.random () * MAX_Y );
            g2d.drawLine ( 0, y1, MAX_X, y1 );

        }

        return outImage;
    }

    private static void paindBoxes ( Graphics2D g2d, int MAX_X, int MAX_Y )
    {
        int colorId = ( int ) ( Math.random () * RANDOM_BG_COLORS.length );
        g2d.setColor ( RANDOM_BG_COLORS[ colorId ] );
        g2d.fillRect ( getRandomX ( MAX_X ), getRandomY ( MAX_Y ), getRandomX ( MAX_X ),
                getRandomY ( MAX_Y ) );
    }

    private static int getRandomX ( int max_x )
    {
        return ( int ) ( Math.random () * max_x );
    }

    private static int getRandomY ( int max_y )
    {
        return ( int ) ( Math.random () * max_y );
    }

    private static void setRandomFont ( Graphics2D g2d )
    {
        Font font = new Font ( "dialog", 1, 33 );
        g2d.setFont ( font );
    }

    private static void setRandomFGColor ( Graphics2D g2d )
    {
        int colorId = ( int ) ( Math.random () * RANDOM_FG_COLORS.length );
        g2d.setColor ( RANDOM_FG_COLORS[ colorId ] );
    }

}
