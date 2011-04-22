# Euclid.rb

# Requires the bindings and parser. Creates new GUI. Defines anything new needed for the GUI

require 'bindings.rb' #load Java bindings
require 'parser.rb' #load Parser

#create GUI
$euclid = REPL.new(RParser.new());
$euclid.setTitle("Euclid");
