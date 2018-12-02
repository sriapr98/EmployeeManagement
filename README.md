# EmployeeManagement
Important steps before proceeding

1)Please change the IP address on res/values/strings.xml
    i)GLOBAL IP:  Input IP found in whatsmyip.net in case of testing with different networks(mobile and server).
    ii)LOCAL IP:Input IP found using ifconfig or ipconfig in case of testing with same networks(mobile and server). 
    iii)Also check if Port forwarding is enabled on port 80(HTTP) in router settings.
    
2)Technologies used in server side.
  i)Apache2 server.
  ii)mysql database.
  iii)phpmyadmin.

2)Please change the Username,Password and DB name of mysql in all the server side PHP files(edit.php,addupdate.php and viewall.php).

3)Use the same username and password(mysql) to login to phpadmin.

4`)Libraries Used
    i)Volley(For making network requests).
    ii)Gson(For handling JSON responses).
