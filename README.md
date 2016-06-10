# Process Controller

Controlling processes and maintaining system status can be a tedious task when done through multiple commands in a terminal.
To ease the workflow, Process Controller adds a customizable web GUI to handle all commands.

- [Technical flow](#)
- [How to set up the project](#)
	- [Properties](#)
- [Possible TODOs](#)
- [Q/A](#)

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

Note: The server is not multithreaded, thus will only one process be served at the time. The server has no built-in security (running HTTP, no file access checks, etc...)

## How to set up the project

### Properties
`webserver.port` specifies webserver port (default value=8000)  
`webserver.indexpage` specifies index page of webserver (default value=index.html)  
`exec.path` specifies path to executable (default value=C:/cygwin/bin/bash.exe)  

## Possible TODOs
* Make the Java server multithreaded to handle more calls at once.
* HTTPS

## Q/A
Q: Can I use this as an asynchronous terminal editor?  
A: No, the server can only handle one process at the time.

Q: Can I send input to a process after starting it?  
A: Currently no.

Q: Can I see output in real time from a process?  
A: Nope, HTTP does not support streaming responses.
