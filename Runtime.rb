# Runtime.rb

# The parser class. Extends the Ruby programming language with some syntactic sugar specific to Euclid.
# As the parser class is doing the evaluating, any instance variables defined will
# remain with it, therefore, in euclid, variables should begin with an @, and constants with a
# Capital Letter. Constants are defined in this file (as they are really just Vars)

# Methods specific to the runtime but not object can also be defined here, eval 
# will recognise and run them. However, they should be defined in a Module
# (probably in bindings) and then mixed in to Runtime.

#Note: assumes relevent java classes are already loaded 

class Runtime
		
	
	# include Math methods and constants
	include Math

	#define constants

	# Makes evaluating user input so much easier
	# As operators are defined, users can multiply by I in order to create an imaginary number 
	I = Complex.new(0, 1) 
	
	def zxc()
		puts "hello"
	end

	def parse(s)
		#buggy/ambiguous, needs cleaning
		if s =~ /\[\[.+\]\]/ #Matrix niceness
				m = (s=~/\[\[/)
				m_ = (s=~/\]\]/)
				s[m, m_] = "Matrix.new(" + s[m, m_] + ")"
		end
		
		#really buggy/hackish
		if s =~ /\<.+\>/ #vector stuff
			v = (s=~/\</)
			v_ = (s=~/\>/)
			s[v, v_] = "Vector2D.new(" + s[v+1, v_-2] + ")"
		end
		
		return (eval(s)).to_s
	end
	
	def vars
		return self.instance_variables
	end


end

