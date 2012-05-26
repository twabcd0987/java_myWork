select tc.tcno 
from tc inner join class on tc.cno=class.cno
where class.term=1 and not exists ( select sc.tcno
                    from sc
                    where sc.sno=1001 and sc.tcno=tc.tcno 
                  );