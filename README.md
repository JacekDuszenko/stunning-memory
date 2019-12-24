# stunning-memory
A program that lets you check out and visualize which Java class names are currently the most popular on Github.

##Running
You can run your program within IntelliJ IDEA but you won't experience auto-refresh that happens when coroutine ends examining repository,
because terminal window is cleared at that moment (unfortunately IntelliJ 'terminal' is not the real terminal and does not allow for clearing content).
1. Build the jar with `./gradlew jar`
2. Run with .. TO BE DONE

## Remarks
Unfortunately github API allows only for 30 calls per minute. Due to this rate limiting, the program execution flow looks as if examined repository
data was fetched in chunk of 30 calls. I decided not to implement delay on the coroutine level so substantial part of `/search/repository` github API 
calls return code 403 as response (which is ignored).