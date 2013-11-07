cloaked-octo-cyril
==================

__Description:__

This is a continous delivery example with an Android application using gradle as the build system and dependency manager, Robospice for asynchronous networking inside the Android app, Instrumentation framework for functional testing, Support Library v4 and v7 for compatibility, Travis as the continuous integration server and TestFlight as the deployment service. I'll publish the detail setup and explanations for all these in my blog very soon.

__Travis CI Status__

[![Build Status](https://travis-ci.org/Ruenzuo/cloaked-octo-cyril.png)](https://travis-ci.org/Ruenzuo/cloaked-octo-cyril)

__Scenario:__

This example shows you how to configure an Android application with two product flavors (free and paid) taking advantage of the new build system to use different assets for each application, implemented different features through build config injection and test and deploy each app individual with Travis and TestFlight. I found this especially useful when you are on a project with a large team divided and each part is developing/testing different features on the same application.

__Building and Testing:__

In order to build and test the application, clone this repo:

```sh
$ git clone git@github.com:Ruenzuo/north-american-ironman.git
```

Then execute the tests with the gradle wrapper, you will need some device or emulator connected to adb before:

```sh
$ cd north-american-ironman && ./gradlew connectedInstrumentTest
```

![cd-free-screenshot-1.png](https://dl.dropboxusercontent.com/u/99114459/cd-free-screenshot-1.png)&nbsp;
![cd-free-screenshot-2.png](https://dl.dropboxusercontent.com/u/99114459/cd-free-screenshot-2.png)
![cd-paid-screenshot-1.png](https://dl.dropboxusercontent.com/u/99114459/cd-paid-screenshot-1.png)&nbsp;
![cd-paid-screenshot-2.png](https://dl.dropboxusercontent.com/u/99114459/cd-paid-screenshot-2.png)

For more information on how to get started with each tool/framework, go to:  
gradle: https://github.com/gradle/gradle  
Robospice: https://github.com/octo-online/robospice  
Instrumentation: http://developer.android.com/tools/testing/index.html  
Support Library: http://developer.android.com/tools/support-library/index.html  
Travis: https://travis-ci.org/  
TestFlight: https://testflightapp.com/

__To-Do__

* `[✓]` <del>Make it public.</del>
* `[ ]` Write the blog post.
* `[ ]` Write better tests. Currently I'm doing some ugly workarounds for testing ads, I'm accepting suggestions on this :)

License
=======

    The MIT License (MIT)

    Copyright (c) 2013 Renzo Crisóstomo

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in
    the Software without restriction, including without limitation the rights to
    use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
    the Software, and to permit persons to whom the Software is furnished to do so,
    subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
    COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
    IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
    CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
