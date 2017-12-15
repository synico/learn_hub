## Expect命令
用来处理交互的命令，包括最关键的四个命令。

#### send
接收一个字符串参数，并将该参数发送到进程。

#### expect
等待一个进程的反馈，可以接收一个字符串参数，也可以接收正则表达式参数。

#### spawn
用来启动新的进程，spawn后的send和expect命令都是和spawn打开的进程交互的。

#### interact