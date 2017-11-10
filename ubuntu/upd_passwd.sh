#!/usr/bin/expect -f

set hostname [lindex $argv 0]
set old_password [lindex $argv 1]
set new_password [lindex $argv 2]

spawn ssh $hostname
expect {
    "(yes/no)" { send "yes\r";exp_continue }
    "password:" { send "$old_password\r" }
}
expect "$*" { send "passwd\r" }
expect "*password:" { send "$old_password\r" }
expect "New password:" { send "$new_password\r" }
expect "Retype new password:" { send "$new_password\r" }

interact

