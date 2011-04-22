# parser.rb

# The parser class. Extends the Ruby programming language with some syntactic sugar specific to Euclid.

class RParser
	
	def parse(s)
		if s =~ /\[\[/ #Matrix niceness
				i = (s=~/\[\[/)
				i_ = (s=~/\]\]/)
				s[i, i_] = "Matrix.new(" + s[i, i_] + ")"
		end
		
		return (eval(s)).to_s
	end


end

