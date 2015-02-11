import os
from fnmatch import fnmatch
import shutil

def unique(seq):
    seen = set()
    return [seen.add(x) or x for x in seq if x not in seen]

print "---File Extractor---"
print ""

root = raw_input("Directory to extract files from: ")

print "Analyzing filetypes..."

extensions = []

for path, subdirs, files in os.walk(root):
    for name in files:
        ext = name.split(".")[-1]
        extensions.append(ext)

extensions = unique(extensions)

print "File types available: "

for ext in extensions:
    print ext

target = raw_input("Directory to save extracted files to: ")
pattern = raw_input("File pattern: ")

for path, subdirs, files in os.walk(root):
    for name in files:
        if fnmatch(name, pattern):
            x = os.path.join(path, name)
            print "File found: " + x
            shutil.copy2(x , target)
