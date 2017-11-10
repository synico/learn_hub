#! /bin/bash

echo "Update password for $1"
readonly func="upd_"$1

#Dev2
upd_dev2() {
    echo "***********start to update password for Dev2(caraway1,catalpa1)***********"
    echo "***********end to update password for Dev2(caraway1,catalpa2)***********"
}

#Test2
upd_test2() {
    echo "***********start to update password for Test2(chama1,chelsea1,chelsea2)***********"
    ./upd_passwd.sh chama1 oct2017nick nov2017nick
    ./upd_passwd.sh chelsea1 may2017nick nov2017nick
    ./upd_passwd.sh chelsea2 may2017nick nov2017nick
    echo "***********end to update password for Test2(chama1,chelsea1,chelsea2)***********"
}

#Test3
upd_test3() {
    echo "***********start to update password for Test3(caddo1,caddo2,caddo3)***********"
    ./upd_passwd.sh caddo1 oct2017nick nov2017nick
    ./upd_passwd.sh caddo2 may2017nick nov2017nick
    ./upd_passwd.sh caddo3 may2017nick nov2017nick
    echo "***********end to update password for Test3(caddo1,caddo2,caddo3)***********"
}

#Test4
upd_test4() {
     echo "***********start to update password for Test4(cayuga1,cayuga2,cayuga3)***********"
     ./upd_passwd.sh cayuga1 jul2017nick nov2017nick
     ./upd_passwd.sh cayuga2 apr2017nick nov2017nick
     ./upd_passwd.sh cayuga3 apr2017nick nov2017nick
     echo "***********end to update password for Test4(cayuga1,cayuga2,cayuga3)***********"
}

$func
