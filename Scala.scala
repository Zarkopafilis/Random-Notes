Traits are amazing....partial implementation FTW

Theres an issue with all these high level stuff and lambdas and stuff :
Compiled bytecode gets bigger -> stack traces get HUGE and not easy to understand.

def func(call_by_name: => Something , call_by_value: Something)
