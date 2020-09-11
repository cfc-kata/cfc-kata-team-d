insert into t_contract_limit_age(amt, min_age,max_age)
values(10000, 18,20);

insert into t_contract_limit_age(amt, min_age,max_age)
values(50000, 20,30);

insert into t_contract_limit_age(amt, min_age,max_age)
values(200000, 30,50);

insert into t_contract_limit_age(amt, min_age,max_age)
values(30000, 50,60);

insert into t_contract_limit_age(amt, min_age,max_age)
values(10000, 60,70);

insert into t_contract_limit_age(amt, min_age,max_age)
values(0, 70,1000);


insert into customer(id,name,id_no,mobile_phone) values('10000','zhangsan','411522198405320000','1300000');

insert into t_contract(id,customer_id, amt,draw_amt,rate,term,status)
values('10000', '10000',10000,0,0.38,24,'ACTIVE');

