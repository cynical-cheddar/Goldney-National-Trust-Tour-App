
# Product Evaluation

Once the Goldney Tour Guide application was built to an adequate functional standard, a variety of investigative methods were used to help in refining our design before presenting the finished product to the end-user.

Investigations were carried out after each successive deployment (MVP, Beta, and Release) in order to gauge the feedback from our clients and ensure that our development plan was meeting their needs.

## Development Cycle | Iterative Design

Throughout the development process, we held regular meetings with our clients and stakeholders to ensure that our development goals aligned with the needs of our client.
Later in the development process, we found it to be of less importance to meet as regularly with our clients since we had set out our requirements specification prior. Most time was devoted to working on the android app and web interface.

Meetings that were of particular importance early in the development cycle were documented and are available below:

Password: *GoldneyAR2020*

 1. [Initial Brief](https://uob-my.sharepoint.com/:w:/r/personal/jd18847_bristol_ac_uk/_layouts/15/Doc.aspx?sourcedoc=%7B9FCA6354-B8C7-4BA7-A90E-C94BB3C57B14%7D&file=Meeting0_OurBrief.docx&action=default&mobileredirect=true)
 2. [Meeting Stakeholders and Exploring Current Problems](https://uob-my.sharepoint.com/:w:/r/personal/jd18847_bristol_ac_uk/_layouts/15/Doc.aspx?sourcedoc=%7BB9602F23-15A2-4882-A346-BB58203F5426%7D&file=Meeting2_Questions%20for%20Clients.docx&action=default&mobileredirect=true)
 3. [Discussing Solutions and History Collaboration Invitation](https://uob-my.sharepoint.com/:w:/r/personal/jd18847_bristol_ac_uk/_layouts/15/Doc.aspx?sourcedoc=%7B5F735BD0-54C8-4877-81B4-9399B441E4BC%7D&file=Meeting3_Notes.docx&action=default&mobileredirect=true)
 4. [Adobe XD Prototype Demonstrations](https://uob-my.sharepoint.com/:w:/r/personal/jd18847_bristol_ac_uk/_layouts/15/Doc.aspx?sourcedoc=%7B022F49A1-48C7-405E-A669-A4F4B69FD88E%7D&file=Meeting5_ClientMeeting2.docx&action=default&mobileredirect=true)
 5. [Jordan's Meeting with the History Department](https://uob-my.sharepoint.com/:w:/r/personal/jd18847_bristol_ac_uk/_layouts/15/Doc.aspx?sourcedoc=%7BFE2CEBCF-296A-445C-A09D-F9CA956CD79F%7D&file=Week6.5_HistoryMeetingQuestions.docx&action=default&mobileredirect=true)
 6. [Project Timeline Development Plan](https://uob-my.sharepoint.com/:x:/r/personal/jd18847_bristol_ac_uk/_layouts/15/Doc.aspx?sourcedoc=%7B2D345DCD-B434-4AE1-91DB-009C70A1B968%7D&file=Week0_GoldneyARAgileDates.xlsx&action=default&mobileredirect=true)
 7. [Post-Christmas Meeting](https://uob-my.sharepoint.com/:w:/r/personal/jd18847_bristol_ac_uk/_layouts/15/Doc.aspx?sourcedoc=%7B1C3E3084-2755-493E-A321-C74D223EADEB%7D&file=Week13_NewYearMeeting.docx&action=default&mobileredirect=true)

## Final Release | Evaluation and Refinement

Unfortunately, due to COVID-19, our final evaluation and refinement could not go ahead as initially planned. Please see our COVID-19 Statement to understand how we mitigated these effects.

### Live User Testing:
After meeting all of the requirements set out as per the *Final Release* section in the requirements document, it was time to fully test the functionality of our product in a live environment. This was a multi-faceted process since our software has multiple points of access. As a result, we needed to comprehensively evaluate:

#### The Android Application
The android application is what is to be downloaded by visitors to the gardens. It was evaluated with an **observation** in a live environment, as well as a follow up informal **interview** with a group of closed beta testers.

To facilitate app testing, the Google Play Console beta testing framework was used; allowing an app to be uploaded to the Play Store without showing up in listings. We chose this framework because it is our target release platform and it allows for multiple versions of an application bundle to be in a production pipeline at once, meaning that once everything in a build has been tweaked to requirements, it can be immediately made live. The Play Store is also a familiar environment to most users, therefore the installation process was as close to real use as possible.

A testing environment was created in the real world, with pre-printed QR codes corresponding to existing tiles.
A selected group of testers with android devices were simply told to download the 'Goldney Tour Guide' app after being given a Play Store key and were let loose.

The live test footage is available [here.](https://www.youtube.com/watch?v=wuClfFS_j-E&feature=youtu.be)
The live test summary is available [here.](https://bitbucket.org/goldneyar/goldney-ar/src/b0690630f61c/Documentation/Live%20Test%20Report.md?at=masterBranch)

#### The Content Upload Web Application
The content upload webapp was created as an open platform to submit tiles to our PostGre database so that that they may be displayed in the android app.

Since our clients; Kasha and Alan were keen to make our software project an interdisciplinary endeavour, the content upload form was sent out to a group of interested students and staff in the history department. This was carried out in the style of a **closed beta test**.

The upload form included a set of step by step instructions detailing how to upload your tile and edit it as per our section documentation. Additionally, a video tutorial is included on the page showing users the exact design process.

The team's email address is given to all visitors to the site and feedback is encouraged. As of yet, no feedback has been provided by the users of the site.

The content upload form is available [here.](https://create.goldneyhall.com/)

#### The Web Administrator Application

 The administrator webapp is a private section of the upload form. Requiring a username and password to access; it is only available to our Software Product Engineering team as well as our clients.

 An **observation** was carried out to determine whether our target users were able to effectively navigate our interface with minimal help. This was carried out over a Skype for Business call with our both our clients. We utilised the built-in screen sharing capability to view their desktop activity while we asked Kasha and Alan to follow the *'think aloud'* process to gain insights into their thought process. They preferred not to be recorded.

 The results of both tests showed that, although the site was navigable with ease, the UI lacked a certain responsive feel. Acting upon this feedback, dialogue toasts were implemented into the React layer when actions were completed.
