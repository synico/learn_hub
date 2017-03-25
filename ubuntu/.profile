# ~/.profile: executed by the command interpreter for login shells.
# This file is not read by bash(1), if ~/.bash_profile or ~/.bash_login
# exists.
# see /usr/share/doc/bash/examples/startup-files for examples.
# the files are located in the bash-doc package.

# the default umask is set in /etc/profile; for setting the umask
# for ssh logins, install and configure the libpam-umask package.
#umask 022

# if running bash
if [ -n "$BASH_VERSION" ]; then
    # include .bashrc if it exists
    if [ -f "$HOME/.bashrc" ]; then
	. "$HOME/.bashrc"
    fi
fi

# set PATH so it includes user's private bin directories
PATH="$HOME/bin:$HOME/.local/bin:$PATH"

# set USER ENV
JAVA_HOME="/opt/java/current"
export JAVA_HOME
PATH="$JAVA_HOME/bin:$PATH"

MVN_HOME="/opt/maven/current"
PATH="$MVN_HOME/bin:$PATH"

ECLICPSE_HOME="/opt/eclipse/current"
PATH="$ECLICPSE_HOME:$PATH"

HBASE_HOME="/opt/hbase/current"
PATH="$HBASE_HOME/bin:$PATH"




