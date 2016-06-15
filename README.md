# Process Controller

Controlling processes and maintaining system status can be a tedious task when done through multiple commands in a terminal.
To ease the workflow, Process Controller adds a customizable web GUI to handle all commands.

- [Technical flow](#technicalflow)
- [How to set up the project](#howtosetuptheproject)
	- [Properties](#properties)
- [Demo](#demo)
- [Possible TODOs](#possibletodos)
- [Q/A](#qa)

## Technical flow
<p align="center">
  <img src="https://raw.githubusercontent.com/Miicroo/process-controller/master/process%20controller%20flow.png" alt="Main flow"/><br />
  <i>Message flow in Process Controller</i>
</p>

Process Controller's UI is an HTML web GUI with a logic layer written in JavaScript. The JavaScript calls a light-weight Java server through an HTTP REST API. All calls are asynchronous AJAX requests,
thus enabling long-running processes (it is in the nature of asynchronous calls to never time out).
The web GUI is also hosted by the Java server to [avoid cross-domain problems](http://stackoverflow.com/questions/10752055/cross-origin-requests-are-only-supported-for-http-error-when-loading-a-local).

When a REST call is received by the Java server, it looks up a correct RequestParser. By default there is a (non-secure) FileRequstParser which serves the GUI files to the browser.
Additional RequestParsers are used to control processes, sending the desired parameters to a POJO. Any POJO can control a process using the ProcessController interface. The interface is currently
only supporting synchronous calls to process. Thus, when a process is started the Java backend will wait for the process' exit code. ProcessController also returns the output of the program.

**A few things to consider:** This project should be run on a (non-production) machine to control the state of the machine. The server is not multithreaded, thus will only one process be served at the time. The server has no built-in security (HTTP instead of HTTPS, no file access checks, etc...). For more things see [possible TODOs](#possibletodos). 

## How to set up the project

### Properties
`webserver.port` specifies webserver port (default value=8000)  
`webserver.indexpage` specifies index page of webserver (default value=index.html)  
`exec.path` specifies path to executable (default value=C:/cygwin/bin/bash.exe)  
`system.encoding` specifies character encoding of text used in the system (default value=UTF-8)    

## Demo


## Possible TODOs
* Make the Java server multithreaded to handle more calls at once.
* HTTPS

## Q/A
Q: Can I use this as an asynchronous terminal editor?  
A: No, the server can only handle one process at the time. (See [possible TODOs](#possibletodos))

Q: Can I send input to a process after starting it?  
A: Nope.

Q: Can I see output in real time from a process?  
A: Nope, HTTP does not support streaming responses.

Q: Why would I use this instead of a real terminal?  
A: It is a (fairly) simple way to control processes, especially long-runnings commands like "start/stop/compile some program". However, if you are more into terminals and feel that typing commands is sufficient, this project is completely unnecessary.
