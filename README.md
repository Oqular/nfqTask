nfqTask
Home page has only title.
Book appointment is a page where customer can book 
an appointment, he need to fill in his name, surname
and choose specialist that he wants to visit, after
filling all the nessessary information customer 
can press "Book an appointment". After that he gets
redirected to the page where he can see information
that he provided and 2 generated codes: registration
and validation. Registration code is needed to see
department screen since requirement was that
department screen can not be publicaly accessible
and validation code is need so that it would be 
possible to identify person if he wants to see
the time left to his appointment or cancel the
appointment. Check time page asks for both codes
that customer gets, and if provided codes are
verified as existing he can see time left to his
appointment and cancel button with which he can
cancel the appointment. Departmen screen page
requires reservation code and if given code
exists customer can see current and next 5 visits
to the specialist that he is registered. Department
screen shows only visits to the specialist that
customer is registered. Login page is for specialist
they can login to their profiles and view all of 
their own appointments. There are 2 specialist in 
the database(u=sshan,p=sshan and u=jjhan,p=jjhan).
Specialist in their appointment page can see the
list of customers that registered to him. He can 
start the appointment of a customer as well as 
finish/end it. Like cutomers specialist can cancel
any customer registration that is regitered to
them. When logged in specialist can see their
username at the right top corner as well as log out
button(did not find a better place to put it).
Database that is connected is hosted on a free
platform.