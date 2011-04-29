# Euclid.rb

# Requires the bindings and parser. Creates new GUI. Defines anything new needed for the GUI

require 'bindings.rb' #load Java bindings
require 'Runtime.rb' #load Parser

#create GUI
$euclid = REPL.new(Runtime.new());
$euclid.setTitle("Euclid");
