# Euclid.rb

require 'bindings.rb' #load Java bindings
require 'parser.rb' #load Parser

#create GUI
$euclid = REPL.new(RParser.new());
$euclid.setTitle("Euclid");
