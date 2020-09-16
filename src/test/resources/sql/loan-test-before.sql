insert into t_loan(loan_id, contract_id, apply_amount, total_month,
		interest_rate, withdraw_bank_account, repayment_bank_account,
		repayment_type, status, version)
values('loan0001', 'C0001', 100, 12,0.10,'BankAccount001','BankAccount002','DEBX','init', 1);


insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0001', 'loan0001', 1, '2020-10-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0002', 'loan0001', 2, '2020-11-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0003', 'loan0001', 3, '2020-12-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0004', 'loan0001', 4, '2021-01-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0005', 'loan0001', 5, '2021-02-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0006', 'loan0001', 6, '2021-03-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0007', 'loan0001', 7, '2021-04-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0008', 'loan0001', 8, '2021-05-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0009', 'loan0001', 9, '2021-06-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0010', 'loan0001', 10, '2021-07-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0011', 'loan0001', 11, '2021-08-16', 11, 1, 10, 'PLAN', 1);

insert into t_repayment_plan(id, loan_id, period_no, payable_date, payable_amount, payable_interest, payable_capital, status, version)
values('p0012', 'loan0001', 12, '2021-09-16', 11, 1, 10, 'PLAN', 1);