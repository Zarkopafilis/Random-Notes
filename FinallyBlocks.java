NEVER USE FINALLY BLOCKS FOR REAL-TIME STUFF.
Its supposed to be the last but eh...least safety net.
*Will get executed on vm termination
*Will stack up and be queued as other stuff take place (Garbage collection etc...)
Better add post-exception code on catch block.
Finally blocks may be sooooo delayed till they get executed!
