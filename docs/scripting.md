With the `script` command, you can execute simple scripts stored in text
files, enhancing the existing command-line commands with special script 
commands and output filters. Script commands allow you to search for files
or directories, store and remove variables, iterate variable arrays, etc.
Output filters are used to filter the output of commands, e.g., restrict
the information output or redirect it to a file.

Script variables are of the format `${name}`, with `name` being the name
of the variable. Environment variables are of the format `@{name}`. 
Variables get automatically replaced in commands before these get executed.

The following script iterates through all ARFF files in directory 
`/home/fracpete/development/datasets/uci/nominal`
and cross-validates J48 on it. From the output generated by J48, only
the lines containing `Time taken`, `Correctly Classified` and `Root mean squared`
are filtered out, using the `grep` filter. This output is then also stored
in the file `/home/fracpete/j48.txt`, using the `tee` filter.

In order to make scripts more readable, especially when performing filtering,
long lines can be broken down into multiple ones by ending them all but the
last one with a backslash (`\`). 

Empty lines are skipped, as well as comment lines starting with `#`.

```
# setup
set searchdir=/home/fracpete/development/datasets/uci/nominal
set outfile=/home/fracpete/j48.txt
set env=weka392

# search for arff files
echo --message "\nsearch dir: ${searchdir}"
list_files --dir ${searchdir} --regexp ".*\.arff" --dest files

# evaluate J48 on each of the arff files and output accuracy in text file
foreach --iterate files --dest file
  echo --message "\n--> ${file}" | tee --stdout --append --output ${outfile}
  run ${env} --class weka.classifiers.trees.J48 -t ${file} \
     | grep --stdout --regexp ".*(Time taken|Correctly Classified|Root mean squared).*" \
     | tee --stdout --append --output ${outfile}
```

# Help
If you want to get more help on filters, use the following commands:

* `filter_help` -- outputs a list of all available filters 
* `filter_help --filter <name>` -- outputs a detailed help screen for the named filter

If you want to get more help on script commands, use the following commands:

* `script_help` -- outputs a list of all available script commands
* `script_help --cmd <name>` -- outputs a detailed help screen for the named script command


## Script commands
The following script commands are available:
```
Available script commands:

base_name <options>
	Extracts the file name without the path from the specified variable.

calc <options>
	Calculates the result of a mathematical expression.

del_dir <options>
	Deletes the specified directory (recursively).

del_file <options>
	Deletes the specified file.

dir_name <options>
	Extracts the path from the specified file variable.

dump_vars
	Just outputs all the currently set variables.

flatten <options>
	Flattens an array variable into a single string variable.

for <options>
	Iterates through the numeric values from lower to upper bound, using the specified step amount.

foreach <options>
	Iterates through the elements of a variable and executes the nested instructions.

list_dirs <options>
	Locates directories in specified directory and stores them in a variable.
	Search can be recursive, directory names (excl parent path) can be matched again regular expression.

list_files <options>
	Locates files in specified directory and stores them in a variable.
	Search can be recursive, file names (excl path) can be matched again regular expression.

read_lines <options>
	Reads all the lines in a text file into a variable.
	Can skip empty lines and lines that match regular expression.

replace <options>
	Performs string replacement, simple or regular expression based.
	If no replacement string is provided the empty string is used.

replace_ext <options>
	Replaces the extension of the file stored in the variable with the supplied one.

set <args>
	Sets a variable in the form of 'name=value'.
	The value can contain other variables, which will get evaluated in a lazy fashion.

split <options>
	Splits a string variable into a string array variable.

unset <args>
	Removes the specified variable.


Notes:
<options>
	the command supports additional options,
	specify the script's name to output detailed help.
<args>
	the command supports additional arguments
	see script's help.
```


## Output filters
The following output filters are available:
```
Available filters:

grep <options>
	For capturing strings that match a regular expression.

replace <options>
	Performs string replacement, simple or regular expression based.

tee <options>
	Tees off the output to a file.


Notes:
<options>
	the filter supports additional options,
	specify the filter's name to output detailed help.
```