# TextEditor-HyperSkill
Работа над проектом текстового редактора с применением библиотеки SWING. Решение задачи HyperSkill.
# Work on project. Stage 2/4: Saved and loaded
In this stage, you will write a program that can read files from the hard drive, edit them, and save them to the same or any other file.

Notice that if you expand the window, the other elements stay in the same place. But in most applications, you can see that some components get bigger or smaller when you resize the window. To do this, you should apply another layout instead of the null layout that was shown in the first stage.

In Java, there are a lot of different layouts, and the default layout is named BorderLayout. This layout has 5 places to put components: top, bottom, left, right and center. If you execute the code below you can see where these places are. Do not forget to remove setLayout(null) line and add nothing - BorderLayout is a standard layout for JFrame.

Now, let's add working with files. To load a file from a hard drive, the user should specify the name of the existing file. If you run this from an IDE, this file should be in the same folder where the "src" folder is. Then, after the user presses the "Load" button, the content of the file should be displayed in the text field. After editing, you should be able to save the file. To do it, you need to specify a name for the file and then press the button "Save".
