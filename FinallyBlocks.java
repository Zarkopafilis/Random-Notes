NEVER USE FINALLY BLOCKS FOR REAL-TIME STUFF.
Its supposed to be the last but eh...least safety net.
*Will get executed on vm termination
*Will stack up and be queued as other stuff take place (Garbage collection etc...)
Better add post-exception code on catch block.
Finally blocks may be sooooo delayed till they get executed!

Notes for exceptions:
If there is no per-exception-type different handling , instead of multi catch blocks I prefer to just catch 'Exception' 
superclass instead.
