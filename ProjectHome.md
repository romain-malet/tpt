## Important ##

Toolkit Productivity Tools is an extension to a great server-side rich UI [Vaadin framework](http://vaadin.com), providing extra collection of common patterns, widgets and utilities you usually use in almost any vaadin-based web application. This library aims to help you save development time by reusing most commonly used patterns and things.


## Latest News ##

29.04.2011 - Wow, almost a year and 1.2 is out.

Sorry to all tpt-users, had almost no time for proper maintenance of TPT. But this does not mean it was not evolving at all. A lot of new features were tried and tested and all will go to new generation 2.0 of TPT soon ! For now, maintenance version of TPT is out to [directory](http://vaadin.com/directory#addon/toolkit-productivity-tools). This will be the last release of 1.x branch. In the beginning of May 2011, version 2.0 will come out.

  * Fixed some issues
  * Aligned with Vaadin 6.5+
  * PDF Viewer is removed from TPT to make it depsless. PDF Viewer will start as a separate project shortly, just to make tpt itself lighter and pdf viewer more robust and complex.


26.04.2010 - Maintenance version 1.1.1 is released

  * Fixed issues 4, 5 and 6
  * No native2ascii processing is required anymore for i18n files. Just make them plain text UTF-8 files.
  * Removed optional IcePDF jar files to decrease bundle size
  * PDF Viewer now detects absense of IcePDF jars and displayes a correct message instead of plain NoClassDefFound error
  * Some updates to the manual


18.04.2010 - Version 1.1 is released

  * Aligned with Vaadin 6.3.0
  * new PDF Document Viewer component
  * new TPTMessagePanel component
  * bugfixes


17.12.2009 - A first public version of TPT is published to Google Code. Use featured downloads or just downloads section to get latest binary, demo web application and a small reference book. This version is aligned with the [Vaadin 6.1.5](http://vaadin.com/download) version of the framework (however, works with latest 6.2.2 as well).


## Live Demo ##

Watch a live demo at [http://demo.stor-m.ru/tpt-demo](http://demo.stor-m.ru/tpt-demo)


## What's Inside ##

Generally, TPT provides:

  * Higher level extension to Application class to provide i18n support and ThreadLocal pattern support
  * Extended Window classes to support ESC/Enter keystrokes out of the box as well as html-backgrounded windows
  * Support for i18n (Internationalization) of Vaadin applications - for strings, labels and custom html layouts
  * iPhone-like view controller to manage views within a single window
  * Lazy loading layout to automatically run long UI initialization code in a separate thread
  * PDF viewer component to display large pdf files online without downloading and any acrobat plugins or applets
  * Standrad, JOptionPane-like dialogs for displaying quick prompts to the users
  * Number of small widgets, such as: captcha, sizers, etc...
  * Server-side only, works with standard or any custom widgetset and does not require recompilation


## Quick Start ##

  1. Drop tpt-core.jar to your webapp WEB-INF/lib folder. TPT does not have any client-side widgets, so you do not need to recompile anything.
  1. If you plan to use PDF viewer, also drop to WEB-INF/lib the bundled (or download latest) IcePDF core jar files from the distribution "lib" folder or from http://www.icepdf.org
  1. Refer to the beginning of  [TPT Developer Manual](http://tpt.googlecode.com/files/handbook.pdf) to begin using TPT


## Roadmap ##

The following main items are planned for the next version of TPT:

  * Current bugfixes
  * Templating engine support in custom layouts
  * Common windows ( pre-defined, ready to use Window templates for typical application needs - control panel, etc etc
  * Initial SEO support for Vaadin applications

You can read more information from the [TPT Developer Manual](http://tpt.googlecode.com/files/handbook.pdf) and from generated Java Docs help files.

