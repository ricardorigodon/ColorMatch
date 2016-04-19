# ColorMatch
Color application on Android that will tell you if your outfit is a match or not a match. Software Engineering Project for Spring 2016.
Uses android camera to grab colors and clicking match button will tell user if the chosen colors are a match or not.

Added module to Open Source project Camera Color Picker by tvbarthel. https://github.com/tvbarthel/CameraColorPicker

This app likely would not be possible without them.

# Welcome to Color Match.

Here are instructions on how to use my app.


#Installation

Will be on the Play Store in the near future.

For now, zip up this repository open up in Android Studio compile and enable Developer Tools on your android device and sync it with studio to load it on your device.

#Instructions

Open up Color Match on your Android device.

Note: Do not navigate to the Palette page, it is a work in progress. The Color page and the picker is the focus of this demo.

Main colors only are included : Red, Magenta, Yellow, Blue, Cyan, Green, Black, and White. Their shades will likely be the focus of future patches.


#To detect a color

Click the dropper in the bottom right and the Android camera will open.
Navigate the circle in the middle to the color you want to detect and it will glow to that color.
You can also click the lightning bolt in the top right, this turns on flash. Turn this on if you have trouble detecting colors.


#To save a color

If you want to save a color first click the circle with the finger in the center and then click the floppy 'save icon' on the bottom bar right of your color. You should see a picture representation of the color along with its hex code.

GIF : http://gfycat.com/CourteousComplexKingfisher


#To check if the colors match

Here is my module. Go back from the camera to the main page under the Color tab. The left corner hosts the match button where if you click it will pass in your colors into the matching algorithm and output a result. This will be returned in a message where you can see if the colors you chose are a match or not.

For this demo, you can only test lists of 2, 3, or 4 colors. So keep that in mind!

GIF : http://gfycat.com/EnormousSerpentineBoubou


# Color Details

See a color and want to know some details? Click the color and the details such as its hex code, rgb, and hsv value will appear.

Three icons in the top right.

Share where you can share the color details and a picture of the color with your friends, or e-mail, or social media. 

Edit (looks like a pencil) where you can change the name of the color. 

And Delete (looks like a trashcan) where you can delete the color from your list.



# Information

Three dots on main Color Match page in the top right you can either "Contact Us" the original developers of Camera Color Picker telling them how awesome my module is, view the Content & Licenses page with the open source libraries used and their licenses, or see a snippet about this app.


#Acknowledged Bugs

Due to how the Android camera handles distance values, sometimes colors are incorrectly identified leading to a incorrect match value. Plan to address that in future iterations.




# LICENSE

Copyright (C) 2016 Ricardo Rigodon

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
