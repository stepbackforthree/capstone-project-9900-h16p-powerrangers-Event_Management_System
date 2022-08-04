# capstone-project-9900-h16p-powerrangers

## Below list should be installed on local environment.

1. Node.js

2. npm

3. yarn

4. JDK 11

## Front-end setup

### 1. Change to the root directory of project, which should show like below figure:

![1](images/1.png)

### 2. Move into the frontend directory with:

<center><b>cd frontend</b></center>

### 3. In the frontend directory, install the required dependencies with:

<center><b>npm install</b></center>
<center>or</center>
<center><b>yarn install</b></center>
It may take several minutes to complete and present information likes below figure:

![2](images/2.png)

Note that if there is any unexpected error occur, which makes you cannot install some dependencies successfully, then you can also download the pre-installed dependencies called “node_modules” referring to the link:
\_\_\_ ,
and unzip and extract the content in the current directory, then you can move the next step.

### 4. In the current directory, run the frontend project with:

<center><b>npm start</b></center>
<center>or</center>
<center><b>yarn start</b></center>
Then you will see the information likes below figure if everything goes well.
![3](images/3.png)

### 5. Turn on a browser and visit our website via:

<center><b>http://localhost:3000/</b></center>
Now the front-end project is running on the port 3000.

## Back-end setup

### 1. Change to the root directory of project.

### 2. We have already packed the whole backend project into an executable file called “backend.jar”, which you can move into its directory with:

<center><b> cd out/artifacts/backend_jar</b></center>

### 3. To start to backend service via:

<center><b> java -jar backend.jar</b></center>
Then you should see information likes below figure:
![4](images/4.png)
Now the back-end service is running on the port 8081.

### 4. If everything goes well till now, the project has completely deployed and worked, enjoy! By the way, if you want to see the interfaces information of this project, you can open a browser and visit:

<center><b>http://localhost:8081/swagger-ui/index.html</b></center>

