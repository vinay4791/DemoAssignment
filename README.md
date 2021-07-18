# RecipeListApp

##  Tools Used :
- MVVM architecture is used for the project with Android ViewModel and LiveData.
- Dependency injection framework KOIN is used
- Used RxJava schedulers for asynchronous data loading.
- Retrofit is used  for Networking and Glide is used for Image Loading.
- Android Navigation Library is used for designing fragments and communicating between them.
- MOSHI Converter Factory is used for Json serialization and de-serialization.
- Google Flex box library is used for showing tags in the app

Architecture :

MVVM Architecture is used

Implementation is done based on https://developer.android.com/codelabs/kotlin-android-training-repository#5 

Architecture Flow :

Activity Communicates to ViewModel
ViewModel Communicates to Repository
Repository makes api call with the help of Fetcher
Fetcher Makes api call with use of a retrofit interface
Abstraction is provided for Fetcher.

Data is passed based on a viewstate concept. Response from api is parsed and converted to a viewstate which the view(Acivity/Fragment) consumes.
The conversion is done using a converter which is basically a rxJava Function.(Please refer viewstate package)

Sealed class of kotlin are used for writing viewstates

For eg : The imageurl received from api is appended with "https:" . These type of conversions are done in converter which takes 
business logic out of viewholder and ensures a smooth scrolling.(Please refer Utils.kt)

Dependency injection :

Koin is used for dependency injection because it works amazingly well with Kotlin.

Navigation Component :
Fragments are managed using the Navigation Component Library 

Injections in a package are done in a module in respective packages.

Base Module :
Activies, Fragments and Viewmodels inherit from BaseActivity, BaseFragment and BaseViewmodel which is written purposely for 
future scaling of the application

Swipe Refresh : Swipe Refresh is added to list for recalling api in case of failed api calls in an error case.

SharedViewModel : Shared ViewModel concept is used to communicate between the fragments. 
