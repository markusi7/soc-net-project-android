# soc-net-project-android
A simple Android app created as a part of Project for Social Networks Course at FER.

App is built upon MVP design pattern.
Networking is done using Retrofit and OkHttp, the API itself was developed by a colleague.


##App functionality

After social login using Facebook, the app fetches liked Facebook movies, movies stored in your watchlist,
IMDB Top 20 movies and those recommended using a recommendation engine developed on backend.

Clicking on a movie allows you to view it's details and add it to your watchlist.

The last feature is search, which displays a list of found movies. Searching is also available by using speech recognition.

##App design

App colors are imported from generated [Material Palette](materialpalette.com).
Used icons are downloaded from [Icon Finder](iconfinder.com).
App follows Google Material Design Guidelines. It uses Views such as [Coordinator Layout](http://developer.android.com/reference/android/support/design/widget/CoordinatorLayout.html) and others from Android Design Support Library.

P.S.
[Here is another example usage of Android Design Support Library](https://github.com/markusi7/design-support-library-example)

