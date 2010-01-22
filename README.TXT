                TOOLKIT PRODUCTIVITY TOOLS

                       version 1.0.1


== CHANGES HISTORY
==================================

Legend:

+ - New feature / improvement
* - Bugfix
? - Information


??.01.2010 v.1.0.1

* - fixed bug #4
? - aligned with Vaadin 6.2.2


17.12.2009 v.1.0.0

* - Numerous small bugfixes
? - Added developer handbook
+ - Added new demo application


09.11.2009 v.1.0.0 RC1

? - Finally, a release candidate of first public version of TPT
? - Added user manual in addition to JavaDoc
? - Removed unnecessary code and refactored packages and clases locations a bit
* - Fixed bug in an application thread helper, which was causing problems for passing currern application instance into ThreadLocal
* - Removed explicit dependency on HttpServlet in a transaction listener implementation of a TPTApplication
+ - Upgraded to Vaadin 6.1.4 release

31.05.2009 v.0.0.3

? - Project is now published at Google Code: http://code.google.com/p/tpt
* - Fixed bug with duplicated views, when NPE error occurs during switching.
+ - Upgraded to Vaadin 6.0.0 release
+ - TPTMultiView now incorporates URIFragmentUtility of Vaadin, in order to automate view switching
    from an URL. By default this function is disabled, you'll have to construct the TPTMultiView
    with a new constructor, that enables fragment utility. See JavaDoc for more info.
+ - TPTMultiView now passes previous view name to the view being activated, which can be useful
    for historical navigation purposes.
+ - TPTMultiView now has a lazy view feature - you may now register view class instead of instance as a view. 
    If class is registered, it will be instantiated later automatically, when view will be 
    activated (switched) for the first time.

15.03.2009 v.0.0.2

+ Upgraded to final 5.3.0 release of ITMill Toolkit.
+ TPTCaptcha component for representing a captcha image in the UI.
+ RandomPasswordGenerator component, mainly used for TPTCaptcha but can be also used as standalone utility class.
+ TPTMultiView component for managing multiple UI views.
* Cosmetic changes in demo application.


19.02.2009 v.0.0.1

? Sources separated into a standalone library and moved to contrib section.




== WHAT IS THIS
==================================
Toolkit Productivity Tools aka TPT is a small extension to the Vaadin library and aims
to provide extended widgets, utilities and helpers that increases productivity when creating
applications using the Toolkit. TPT was created after I managed to create a couple of Toolkit-based
applications and noticed that a number of small things are commonly used and can be extracted 
to a common library to boost the productivity and eliminate code duplicates.


== BUILDING
==================================
You'll need Java 1.6+ and Apache Ant 1.6+ to build the project. Just run "ant" from the project base
folder and it will compile and create tpt.jar and tpt-demo.war files in the "dist" folder. The war
file is then ready to be dropped to any web contaner or app server to see the small demo showcase of
the components.


== DISCLAIMER
==================================
TPT provided as is, in hope that somebody else will also find it useful or find some ideas or
examples for its own code. 


== LICENSE
==================================
TPT is absolutely free and uses version 2 of Apache License, please see LICENSE.TXT for more details.


== BUG REPORTING AND OTHER CONTACTS
==================================
TPT is hosted at Google Code, please check the homepage: http://code.google.com/p/tpt

- If you want to post a new bug / feature request - please create a ticket here: http://code.google.com/p/tpt/issues/list
- If you want to share something or need more documentation - take a look at wiki: http://code.google.com/p/tpt/
- If you want the latest source: download packages or checkout the sources from svn: http://code.google.com/p/tpt/downloads/list
- You are also welcome to join the project.
