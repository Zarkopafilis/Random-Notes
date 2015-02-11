import os
from fnmatch import fnmatch
import shutil

def unique(seq):
    seen = set()
    return [seen.add(x) or x for x in seq if x not in seen]

print "---File Extractor---"
print ""

root = unicode(raw_input("Directory to extract files from: "))

print "Analyzing filetypes..."

extensions = []

i=0

for path, subdirs, files in os.walk(root):
    for name in files:
        ext = unicode(name).split(".")[-1]
        extensions.append(ext)
        i++

extensions = unique(extensions)

print "File types available: "

for ext in extensions:
    print ext

print "Total files: " + i

target = unicode(raw_input("Directory to save extracted files to: "))
pattern = unicode(raw_input("File pattern: "))

i=0

for path, subdirs, files in os.walk(root):
    for name in files:
        if fnmatch(unicode(name), unicode(pattern)):
            x = unicode(os.path.join(unicode(path), unicode(name)))
            print "Copying file: " + x
            shutil.copy2(unicode(x) , unicode(target))
            i++

print "Total files moved: " + i
