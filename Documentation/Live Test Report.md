# Live Release Testing Report
-
#### 04/05/2020

## Background

The original plan was to carry out a live release test of the Goldney Tours app with a selection of ~20 volunteers around Goldney Gardens. This would closely mirror the environment in which the app would be used after release.
Due to the Covid-19 pandemic and social distancing guidelines, this approach became unfeasible and irresponsible. Furthermore, all members of our development team are not currently residing in Bristol at the present moment.

As a result, Jordan set up a miniature use test environment in his garden at home. New tiles were created and populated with information relevant to garden features in order to simulate the default environment.

### Areas of Investigation
The test was designed to highlight any issues relating the following points:

 - Intuitiveness of navigation.
 - Robustness of update client in a real environment.
 - Audio accessibility features (narration).
 - Robustness of QR code reader. 


### Test Conditions

Volunteers were given a private link to download the app from the Google Play Store under an internal testing agreement. From there, they were simply given the instruction to select 'update from server' upon first starting the app, and were then set free in the garden.

Volunteers were asked to follow the 'think aloud' methodology of observation testing - detailing their thoughts as they navigated the application and interacted with tiles.

In order to test the robustness of the QR code reader, tile codes were placed in a variety of areas with differing levels of lighting to ensure that they were still able to be scanned.

## Results and Findings

  **Intuitiveness of navigation.**
   Users appeared to have no issues navigating the app.
 
 **Robustness of update client in a real environment.**
  The update client functioned correctly on a technical level. However, low download speeds presented an issue. In one use case, the user was able to initiate the update, navigate to the QR scanner, and scan a tile before all resources could be downloaded. These tiles would not display all their information. 
  The app has been patched to fix this. each asynchronous download process now includes a callback to the updater. The user may not scan a tile until all resources have been downloaded.
 
  **Audio accessibility features (narration).**
Audio narration consistently played where applicable. Users asked if there was a way to disable automatic audio playback. Feature will be added in the options menu.

 **Robustness of QR code reader.** 
 The QR code reader was robust and functioned as expected in all use cases.

## The Next Step

Once the app has been updated, the development pipeline in the Google play Console will be moved forward. The Internal Test will progress to a public pre-release build of the app and, if it exists, a beta build can be made live.