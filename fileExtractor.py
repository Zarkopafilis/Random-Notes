import os
from fnmatch import fnmatch
import shutil

def unique(seq):
    seen = set()
    return [seen.add(x) or x for x in seq if x not in seen]

def safeDictGet(dictionary , key):
    if(key in dictionary):
        return dictionary[key]
    else:
        return 0

print "---File Extractor---"
print ""


root = unicode(raw_input("Directory to extract files from: "))

print "Analyzing filetypes..."

extensions = []
nums = {}

i=0

for path, subdirs, files in os.walk(root):
    for name in files:
        ext = unicode(name).split(".")[-1]
        extensions.append(ext)
        nums[ext] = safeDictGet(nums , ext) + 1
        i+=1

extensions = unique(extensions)

print "File types available: "

for ext in extensions:
    print ext + " - " + str(nums[ext]) + " files"

print "Total files: " + str(i)

target = unicode(raw_input("Directory to save extracted files to: "))
pattern = unicode(raw_input("File extensions (Split by '-'): "))

pattern = pattern.split("-")

i=0

for path, subdirs, files in os.walk(root):
    for name in files:
        for ext in pattern:
            if fnmatch(unicode(name) , unicode("*." + ext)):
                x = unicode(os.path.join(unicode(path), unicode(name)))
                print "Copying file: " + x
                shutil.copy2(unicode(x) , unicode(target))
                i+=1

print "Total files moved: " + str(i)

raw_input("Press any key to exit...")
