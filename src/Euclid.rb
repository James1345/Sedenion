require 'java'
require '../alexandria'

REPL = Java::com::alexandria::euclid::REPL
Parser = Java::com::alexandria::euclid::Parser

class RParser
	include Parser
	def parse(x)
		return x
	end
end


euclid = REPL.new(RParser.new());
euclid.setTitle("Euclid");
