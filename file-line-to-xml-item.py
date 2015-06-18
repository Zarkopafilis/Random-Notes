src = "C:\Users\Zarkopafilis\Desktop\\source.txt"
tgt = "C:\Users\Zarkopafilis\Desktop\\target.txt"
src_f = open(src , "r")
src_array = src_f.readlines()
tgt_array = []
for line in src_array:
    line = line.replace("\n", "")
    line = "<item>" + line + "</item>"
    tgt_array.append(line)

src_f.close()
tgt_f = open(tgt,"w")
for line in tgt_array:
    tgt_f.write(line + "\n")
tgt_f.close()
